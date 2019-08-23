package com.example.permissionRoleProject.services;
import com.example.permissionRoleProject.dto.*;
import com.example.permissionRoleProject.entities.*;
import com.example.permissionRoleProject.exception.CustomException;
import com.example.permissionRoleProject.model.Account;
import com.example.permissionRoleProject.model.Location;
import com.example.permissionRoleProject.model.ResponseBean;
import com.example.permissionRoleProject.repository.*;
import com.example.permissionRoleProject.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    LocationRepository locationRepository;

   @Autowired
   PermissionRepository permissionRepository;

   @Autowired
   RolePermissionRepository rolePermissionRepository;


     public UserRole saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
        return userRole;
    }

    public RoleResponse getAllRoles(String username) throws CustomException {
        if (!StringUtils.isEmpty(username)) {
            User user = userRepository.findByUserName(username);
            if (user != null) {
                List<UserRole> userRoles = user.getUserRoleList();
                if (!CollectionUtils.isEmpty(userRoles)) {
                    List<String> roles = new ArrayList<>();
                    for (UserRole userRole : userRoles) {
                        roles.add(userRole.getRole().getRoleName());
                    }
                    return new RoleResponse(true, roles);
                }
            }
            throw new CustomException("user is null !!");
        }
        throw new CustomException("username is empty !!");
    }

    @Transactional
    public ResponseBean<?> signupPage(UserDto userDto)  {
        User user = userRepository.findByUserName(userDto.getUserName());
        if (user == null) {

            User user1 = new User();
            user1.setUserName(userDto.getUserName());
            user1.setEmail(userDto.getEmail());
            user1.setPassword(userDto.getPassword());
//            userRepository.save(user1);
            List<String> roleList = userDto.getRoleNames();
            List<UserRole> userRoleList= new ArrayList<>();
            if(!CollectionUtils.isEmpty(roleList)) {
                for (String rolename : roleList) {
                    Role role1 = roleRepository.findByRoleName(rolename);
                    if(role1 == null){
                        throw new CustomException("role is null");
                    }
                    UserRole userRole = new UserRole();
                    userRole.setRole(role1);
                    userRole.setUser(user1);
                    userRoleList.add(userRole);
//                    userRoleRepository.saveAll(userRoleList);
                }

                user1.setUserRoleList(userRoleList);
                userRepository.save(user1);
                userRoleRepository.saveAll(userRoleList);
                return new ResponseBean<>(true, "user saved successfully");
            }
            else{
                throw new CustomException("role list is empty!!");
            }
        } else {
            throw new CustomException("user already exists!!");
        }
    }


    @Transactional
    public ResponseBean<?> saveLocationPage(LocationDto locationDto) {
      Location location=locationRepository.findByLatAndLng(locationDto.getLat(),locationDto.getLng());
      if(location == null){
          Location location1= new Location();
          location1.setLat(locationDto.getLat());
          location1.setLng(locationDto.getLng());

          locationRepository.save(location1);
          return new ResponseBean<>(true, "location saved successfully");
      }
      else {
          throw new CustomException("role list is empty!!");
      }

//
//            }
//        User user = userRepository.findByUserName(userDto.getUserName());
//        if (user == null) {
//
//            User user1 = new User();
//            user1.setUserName(userDto.getUserName());
//            user1.setEmail(userDto.getEmail());
//            user1.setPassword(userDto.getPassword());
////            userRepository.save(user1);
//            List<String> roleList = userDto.getRoleNames();
//            List<UserRole> userRoleList= new ArrayList<>();
//            if(!CollectionUtils.isEmpty(roleList)) {
//                for (String rolename : roleList) {
//                    Role role1 = roleRepository.findByRoleName(rolename);
//                    if(role1 == null){
//                        throw new CustomException("role is null");
//                    }
//                    UserRole userRole = new UserRole();
//                    userRole.setRole(role1);
//                    userRole.setUser(user1);
//                    userRoleList.add(userRole);
////                    userRoleRepository.saveAll(userRoleList);
//                }
//
//                user1.setUserRoleList(userRoleList);
//                userRepository.save(user1);
//                userRoleRepository.saveAll(userRoleList);
//                return new ResponseBean<>(true, "user saved successfully");
//            }
//            else{
//                throw new CustomException("role list is empty!!");
//            }
//        } else {
//            throw new CustomException("user already exists!!");
//        }

        //return new ResponseBean<>(true, "location saved successfully");
    }




    public ResponseBean<UserListResponse> getUsers() throws CustomException {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                List<UserRole> userRoles = user.getUserRoleList();
                List<String> newRoleList = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    if(userRole != null) {
                        newRoleList.add(userRole.getRole().getRoleName());
                    }
                }
                UserDto userDto = new UserDto();
                userDto.setUserName(user.getUserName());
                userDto.setPassword(user.getPassword());
                userDto.setEmail(user.getEmail());
                userDto.setRoleNames(newRoleList);
                userDtos.add(userDto);
            }
            UserListResponse userListResponse = new UserListResponse();
            userListResponse.setUserList(userDtos);
            return new ResponseBean<UserListResponse>(userListResponse, true, "user data found");
        }
        else {
            throw new CustomException("user is null !!");
        }
    }

    @Transactional
    public ResponseBean<UpdateRoleDto> updateUserRole(UpdateRoleDto updateRoleDto) throws CustomException {
        User user=userRepository.findByUserName(updateRoleDto.getUsername());
        if(user != null){
            if (StringUtils.isEmpty(updateRoleDto.getUsername())) {
                throw new CustomException("username is null !");
            }
            if (StringUtils.isEmpty(updateRoleDto.getRolename())) {
                throw new CustomException("rolename is null !");
            }
              Role newRole = roleRepository.findByRoleName(updateRoleDto.getRolename());
               if (newRole ==null){
                    return new ResponseBean<>(false, "No such role exists!!");
                }
                if (userRoleRepository.findByUserAndRole(user, newRole) == null ) {
                    UserRole userRole = new UserRole();
                    userRole.setUser(user);
                    userRole.setRole(newRole);
                    userRoleRepository.save(userRole);
                } else {
                    throw new CustomException("user with this role already exists !!");
                }

                return new ResponseBean<>(true, "user updated successfully");
              }
            else {
                throw new CustomException("user is null !!");
            }

    }


    public ResponseBean<?> updateUserPassword(UpdatePaswwordDto updatePaswwordDto) throws CustomException{
        if(StringUtils.isEmpty(updatePaswwordDto.getPassword())){
            throw new CustomException("password is null !!");
        }
        if(StringUtils.isEmpty(updatePaswwordDto.getUsername())){
            throw new CustomException("username is null !!");
        }
        User user=userRepository.findByUserName(updatePaswwordDto.getUsername());
          if(user != null) {
            String password = updatePaswwordDto.getPassword();
                user.setPassword(updatePaswwordDto.getPassword());
                userRepository.save(user);
                return new ResponseBean<>(true, "user updated successfully!!");
          }
          else {
            throw new CustomException("user is null !!");
          }
    }

    public ResponseBean<LoginResponse> loginPage(Account account) throws CustomException {
        User user= userRepository.findByUserName(account.getUsername());
        if(user!=null) {
            List<UserRole> userRoles = user.getUserRoleList();
            if (account.getPassword().equals(user.getPassword())) {
               // List<Role> roles = new ArrayList<>();
                List<String> rolesName = new ArrayList<>();
                List<RolePermission> rolePermissions = new ArrayList<>();
                List<RolePermission> rolePermissions1 = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    Role role = userRole.getRole();
                     rolesName.add(role.getRoleName());
                    rolePermissions1 = role.getRolePermissionList();
                    rolePermissions.addAll(rolePermissions1);
                }

                Set<String> pdesc = new HashSet<>();
                List<Permission> permissions = new ArrayList<>();
                for (RolePermission rolePermission : rolePermissions) {
                    Permission permission = rolePermission.getPermission();
                    pdesc.add(permission.getPermissionName());
                }
                LoginResponse loginResponse = new LoginResponse(account.getUsername(), rolesName, pdesc);
                return new ResponseBean<LoginResponse>(loginResponse, true, "login successful !");
                } else
                throw new CustomException("Null Pointer Exception");
             }
          else
               throw new CustomException("Null Pointer Exception");
    }

    @Transactional
    public ResponseBean<?> createPermissions(PermissionDto permissionDto){
        String permissionname=permissionDto.getName();
            Permission permission1=new Permission();
            permission1.setPermissionName(permissionname);


          Permission permission= permissionRepository.save(permission1);
        if(permission != null){
            return  new ResponseBean<>(true,"permissions saved successfully !!");
        }
        else
            return new ResponseBean<>(false,"permissions didnt saved successfully !!");
    }

    @Transactional
    public ResponseBean<RolePermissionDto> createRoles(RolePermissionDto rolePermissionDto){
        Role role=new Role();
        role.setRoleName(rolePermissionDto.getRoleName());
        Role role1=roleRepository.save(role);
        List<RolePermission> rolePermissionList=new ArrayList<>();
        List<String> permissions =rolePermissionDto.getPermissions();
        for(String s:permissions){
            Permission permission=permissionRepository.findByPermissionName(s);
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRole(role1);
            rolePermission.setPermission(permission);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionList=rolePermissionRepository.saveAll(rolePermissionList);
        RolePermissionDto rolePermissionDto1= new RolePermissionDto(rolePermissionDto.getRoleName(),permissions);
        if(role1!=null&&!CollectionUtils.isEmpty(rolePermissionList))
            return  new ResponseBean<RolePermissionDto>(rolePermissionDto1,true,"roles saved succesfully");
        else
            return new ResponseBean<RolePermissionDto>(false,"roles cant be saved succesfully");
    }

    @Transactional
    public ResponseBean<?> deleteUser(String username) throws CustomException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            userRepository.delete(user);
            List<UserRole> userRoles = user.getUserRoleList();
            userRoleRepository.deleteAll(userRoles);

            return new ResponseBean<>(true,"user deleted successfully !");
        }
        else
//            return new DeleteResponse(false, "Not Deleted");
            throw new CustomException( "user is null !!");
    }

    public ResponseBean<PermissionResponse>  getPermissions() {
        List<Permission>permissionList = new ArrayList<>();
        permissionList =permissionRepository.findAll();
        List<String>permissionnamesList=new ArrayList<>();
        for(Permission permission:permissionList)
        {
            permissionnamesList.add(permission.getPermissionName());
        }
        PermissionResponse permissionResponse= new PermissionResponse(permissionnamesList);
        return new ResponseBean<PermissionResponse>(permissionResponse,true,"permission list sent successfully !!");

    }

    public ResponseBean<RoleListResponse> getRoles() {
        List<Role>roleList = new ArrayList<>();
        roleList =roleRepository.findAll();
        List<String>rolenamesList=new ArrayList<>();
        for(Role role:roleList)
        {
            rolenamesList.add(role.getRoleName());
        }
        RoleListResponse roleListResponse= new RoleListResponse(rolenamesList);

        return new ResponseBean<RoleListResponse>(roleListResponse,true,"role list sent successfully !");
     }

    public ResponseBean<UserListResponse> getRolesWithPermissions() throws CustomException {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                List<UserRole> userRoles = user.getUserRoleList();
                List<String> newRoleList = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    if(userRole != null) {
                        newRoleList.add(userRole.getRole().getRoleName());
                    }
                }
                UserDto userDto = new UserDto();
                userDto.setUserName(user.getUserName());
                userDto.setPassword(user.getPassword());
                userDto.setEmail(user.getEmail());
                userDto.setRoleNames(newRoleList);
                userDtos.add(userDto);
            }
            UserListResponse userListResponse = new UserListResponse();
            userListResponse.setUserList(userDtos);
            return new ResponseBean<UserListResponse>(userListResponse, true, "user data found");
        }
        else {
            throw new CustomException("user is null !!");
        }
    }



    public ResponseBean<RolePermissionResponse> getRolesWithPermission() throws CustomException {
        List<Role> roleList = roleRepository.findAll();
        List<RolePermissionDto> rolePermissionDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleList)) {
            for (Role role : roleList) {
                List<RolePermission> rolePermissions = role.getRolePermissionList();
                List<String> newPermissionList = new ArrayList<>();
                for (RolePermission rolePermission : rolePermissions) {
                    if(rolePermission != null) {
                        newPermissionList.add(rolePermission.getPermission().getPermissionName());
                    }
                }
                RolePermissionDto rolePermissionDto = new RolePermissionDto();
                rolePermissionDto.setRoleName(role.getRoleName());
                rolePermissionDto.setPermissions(newPermissionList);
                rolePermissionDtos.add(rolePermissionDto);
            }
            RolePermissionResponse rolePermissionResponse = new RolePermissionResponse();
            rolePermissionResponse.setRolePermissionDtoList(rolePermissionDtos);
            return new ResponseBean<RolePermissionResponse>(rolePermissionResponse, true, "role data found");
        }
        else {
            throw new CustomException("role is null !!");
        }
    }


    @Transactional
    public ResponseBean<?> updateUser(UserDto userDto)  {
        User user = userRepository.findByUserName(userDto.getUserName());

            User user1 = new User();
            user1.setUserName(userDto.getUserName());
            user1.setEmail(userDto.getEmail());
            user1.setPassword(userDto.getPassword());
            userRepository.save(user1);
            List<String> roleList = userDto.getRoleNames();
            List<UserRole> userRoleList= new ArrayList<>();
            if(!CollectionUtils.isEmpty(roleList)) {
                for (String rolename : roleList) {
                    Role role1 = roleRepository.findByRoleName(rolename);
                    if(role1 == null){
                        throw new CustomException("role is null");
                    }
                    UserRole userRole = new UserRole();
                    userRole.setRole(role1);
                    userRole.setUser(user1);
                    userRoleList.add(userRole);
//                    userRoleRepository.saveAll(userRoleList);
                }
                userRoleRepository.saveAll(userRoleList);
                return new ResponseBean<>(true, "user saved successfully");
            }
            else{
                throw new CustomException("role list is empty!!");
            }
    }


}
