package cz.dvorakv.entity.repository.filter;

import cz.dvorakv.constant.UserRole;
import lombok.Data;

@Data
public class UserFilter {

    private UserRole role;

}
