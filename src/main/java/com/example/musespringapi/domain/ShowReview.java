package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShowReview {

    // ** アーティストネーム */
    private String artistName;

    /** 曲名 */
    private String musicName;

    /** ジャケ写 */
    private String musicImage;

    /** 投稿テキスト */
    private String postText;

    /** Post Id */
    private Integer postId;

    private String userName;
    
    /**　ユーザーId */
    private String userNum;

    /** likeの総数 */
    private Integer likeCount;

    /** いいねをしているかの状態 */
    private Boolean likeStatus;

    int getPosrId() {
        return this.postId;
    }

}
