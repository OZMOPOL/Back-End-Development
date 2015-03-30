/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OzClass;

import java.util.List;

/**
 *
 * @author amind
 */
public class OzPost {
    public String pkPostId;
    public String postTitle;
    public String postContent;
    public List<OzPost> comments;
    public String postRoomId;
    public String postUserName;
    public int voteCount;
}
