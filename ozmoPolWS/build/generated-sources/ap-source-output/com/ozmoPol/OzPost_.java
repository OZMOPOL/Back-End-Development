package com.ozmoPol;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzRoom;
import com.ozmoPol.OzUser;
import com.ozmoPol.OzVote;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T01:14:42")
@StaticMetamodel(OzPost.class)
public class OzPost_ { 

    public static volatile CollectionAttribute<OzPost, OzVote> ozVoteCollection;
    public static volatile SingularAttribute<OzPost, String> pkPostId;
    public static volatile SingularAttribute<OzPost, Date> postCDate;
    public static volatile SingularAttribute<OzPost, OzUser> fkPostUserId;
    public static volatile SingularAttribute<OzPost, String> postContent;
    public static volatile SingularAttribute<OzPost, Boolean> postStatus;
    public static volatile SingularAttribute<OzPost, String> postTitle;
    public static volatile SingularAttribute<OzPost, Date> postEDate;
    public static volatile CollectionAttribute<OzPost, OzPost> ozPostCollection;
    public static volatile SingularAttribute<OzPost, OzPost> fkPostPrntId;
    public static volatile SingularAttribute<OzPost, OzRoom> fkPostRoomId;

}