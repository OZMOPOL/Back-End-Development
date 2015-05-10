package com.ozmoPol;

import com.ozmoPol.OzRoom;
import com.ozmoPol.OzUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-10T23:32:08")
@StaticMetamodel(Xuserflwroom.class)
public class Xuserflwroom_ { 

    public static volatile SingularAttribute<Xuserflwroom, String> pkuserXroomid;
    public static volatile SingularAttribute<Xuserflwroom, OzRoom> fkuserXroomroomid;
    public static volatile SingularAttribute<Xuserflwroom, OzUser> fkuserXroomuserid;

}