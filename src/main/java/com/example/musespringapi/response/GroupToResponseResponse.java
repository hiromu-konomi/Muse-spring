package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.GroupToResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupToResponseResponse {

    /** グループ招待に必要な情報 */
    private List<GroupToResponse> groupToResponseList;

}
