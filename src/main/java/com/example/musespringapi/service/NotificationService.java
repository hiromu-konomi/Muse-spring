package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.domain.GroupNotification;
import com.example.musespringapi.domain.LikeNotification;
import com.example.musespringapi.domain.NotificationParents;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.FollowNotificationRepository;
import com.example.musespringapi.repository.GroupNotificationRepository;
import com.example.musespringapi.repository.LikeNotificationRepository;
import com.example.musespringapi.repository.NotificationParentsRepository;
import com.example.musespringapi.repository.PostRepository;
import com.example.musespringapi.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationParentsRepository notificationParentsRepository;
    private final FollowNotificationRepository followNotificationRepository;
    private final GroupNotificationRepository groupNotificationRepository;
    private final LikeNotificationRepository likeNotificationRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //** フォロー、フォロワー時通知を誰が受け取るのかのインサート */
    public void insertFollowNotification(FollowNotification followNotification) {
        followNotificationRepository.save(followNotification);
    }

    /** 誰がどのグループに招待したのかインサート */
    public void insertGroupNotification(GroupNotification groupNotification) {
        groupNotificationRepository.save(groupNotification);
    }

    /** 誰がいいねを押したのかインサート */
    public void insertLikeNotification(LikeNotification likeNotification) {
        likeNotificationRepository.save(likeNotification);
    }

    //** フォロー受け取った人よりフォローした人のfollowNum,typeをselect */
    public List<FollowNotification> getAllByFollowerReceiver(String followReceiver){
        return followNotificationRepository.getFollowNotificationByReceiver(followReceiver);
    }

    //** いいねを受け取った人よりいいねした人のuserNumをセレクト */
    public List<LikeNotification> getLikeNotificationListByReceiver(String likeReceiver){
        return likeNotificationRepository.getLikeNotificationListByReceiver(likeReceiver);
    }

    /** グループ招待を受け取った人より招待した人のuserNum,groupIdをセレクト */
    public List<GroupNotification> getGroupNotificationListByReceiver(String groupReceiver){
        return groupNotificationRepository.getGroupNotificationByReceiver(groupReceiver);
    }

    //** 通知を渡したユーザー情報 */
    public User getUserByTransfer(String transfer){
        return userRepository.userNameList(transfer);
    }

    /** いいねを押されたuserNumをインサートする際に、postIdからReceiverUserNumを持ってくる */
    public Post getPostByPostId(Integer postId) {
        return postRepository.getPostTextUserIdByPostId(postId);
    }

    public NotificationParents getNotiPareByNotiPareId(Integer notiPareId){
        return notificationParentsRepository.getNotiPareByNotiPareId(notiPareId);
    }

}
