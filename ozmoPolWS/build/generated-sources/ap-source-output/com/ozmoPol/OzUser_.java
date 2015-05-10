package com.ozmoPol;

import com.ozmoPol.OzPost;
import com.ozmoPol.OzVote;
import com.ozmoPol.Xuserflwroom;
import com.ozmoPol.Xuserflwuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-10T19:38:25")
@StaticMetamodel(OzUser.class)
public class OzUser_ { 

    public static volatile SingularAttribute<OzUser, Date> userBday;
    public static volatile CollectionAttribute<OzUser, OzVote> ozVoteCollection;
    public static volatile SingularAttribute<OzUser, Boolean> userStatus;
    public static volatile CollectionAttribute<OzUser, Xuserflwroom> xuserflwroomCollection;
    public static volatile SingularAttribute<OzUser, String> userPass;
    public static volatile CollectionAttribute<OzUser, Xuserflwuser> xuserflwuserCollection;
    public static volatile SingularAttribute<OzUser, String> userEmail;
    public static volatile SingularAttribute<OzUser, String> userName;
    public static volatile CollectionAttribute<OzUser, OzPost> ozPostCollection;
    public static volatile SingularAttribute<OzUser, String> pkUserId;
    public static volatile SingularAttribute<OzUser, String> useractHash;
    public static volatile CollectionAttribute<OzUser, Xuserflwuser> xuserflwuserCollection1;

}