package com.ozmoPol;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-10T19:38:25")
@StaticMetamodel(OzVote.class)
public class OzVote_ { 

    public static volatile SingularAttribute<OzVote, OzPost> fkVotePostId;
    public static volatile SingularAttribute<OzVote, String> pkVoteId;
    public static volatile SingularAttribute<OzVote, OzUser> fkVoteUserId;
    public static volatile SingularAttribute<OzVote, Boolean> voteValue;

}