package cydeo.proxy;

import cydeo.model.Comment;

public interface  CommentNotificationProxy {
    default void sendComment(Comment comment){

    }
}
