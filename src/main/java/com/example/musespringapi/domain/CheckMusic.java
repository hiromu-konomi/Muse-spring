package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CheckMusic {

	
	// ** アーティストネーム */
    private String artistName;

    /** 曲名 */
    private String musicName;

    /** ジャケ写 */
    private String musicImage;

    /** music Id */
    private Integer musicId;
    
    private Integer postId;


    /** checkの総数 */
    private Integer checkCount;

    /** チェックしているかの状態 */
    private Boolean checkStatus;

    int getMusicId() {
        return this.musicId;
    }
}
