package pl.jz.webapp.security.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.jz.webapp.security.app.controller.dto.CreateUserRequest;
import pl.jz.webapp.security.app.controller.dto.UpdateUserRequest;
import pl.jz.webapp.security.app.controller.dto.UserResponse;
import pl.jz.webapp.security.app.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (CreateUserRequest createUserRequest);
    UserResponse toUserResponse (User user);
    User toUserFromUpdateRequest(UpdateUserRequest updateUserRequest);
    void updateUser(UpdateUserRequest updateUserRequest,
                      @MappingTarget User user);
}
