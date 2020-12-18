package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.CheckMusic;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class CheckMusicResponse {
	
	private List<CheckMusic> checkAllList;

}
