package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupToResponse {

    /** ID */
    private Integer groupNotificationId;

    /** グループ招待送った人の名前 */
    private String transferUserName;

    /** グループの名前 */
    private String groupName;

    /** グループID */
    private Long groupId;

}
