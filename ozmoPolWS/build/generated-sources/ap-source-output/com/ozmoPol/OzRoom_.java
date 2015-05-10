package com.ozmoPol;

import com.ozmoPol.OzPost;
import com.ozmoPol.Xuserflwroom;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-10T23:32:08")
@StaticMetamodel(OzRoom.class)
public class OzRoom_ { 

    public static volatile SingularAttribute<OzRoom, Boolean> roomStatus;
    public static volatile CollectionAttribute<OzRoom, Xuserflwroom> xuserflwroomCollection;
    public static volatile SingularAttribute<OzRoom, String> roomDesc;
    public static volatile SingularAttribute<OzRoom, String> roomTitle;
    public static volatile SingularAttribute<OzRoom, String> pkRoomId;
    public static volatile CollectionAttribute<OzRoom, OzPost> ozPostCollection;

}