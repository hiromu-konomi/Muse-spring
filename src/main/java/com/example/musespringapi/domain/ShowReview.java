package com.example.musespringapi.domain;

import lombok.Data;

@Data
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

    /** ユーザーId */
    private String userNum;

    /** likeの総数 */
    private Integer likeCount;

    /** いいねをしているかの状態 */
    private Boolean likeStatus;

    /** checkの総数 */
    private Integer checkCount;

    /** チェックしているかの状態 */
    private Boolean checkStatus;

    int getPosrId() {
        return this.postId;
    }

}
