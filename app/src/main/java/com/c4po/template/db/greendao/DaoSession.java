package com.c4po.template.db.greendao;


import com.c4po.template.db.table.DeviceEntity;
import com.c4po.template.db.table.UserInfoEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userInfoEntityDaoConfig;
    private final DaoConfig deviceEntityDaoConfig;

    private final UserInfoEntityDao userInfoEntityDao;
    private final DeviceEntityDao deviceEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userInfoEntityDaoConfig = daoConfigMap.get(UserInfoEntityDao.class).clone();
        userInfoEntityDaoConfig.initIdentityScope(type);

        deviceEntityDaoConfig = daoConfigMap.get(DeviceEntityDao.class).clone();
        deviceEntityDaoConfig.initIdentityScope(type);

        userInfoEntityDao = new UserInfoEntityDao(userInfoEntityDaoConfig, this);
        deviceEntityDao = new DeviceEntityDao(deviceEntityDaoConfig, this);

        registerDao(UserInfoEntity.class, userInfoEntityDao);
        registerDao(DeviceEntity.class, deviceEntityDao);
    }
    
    public void clear() {
        userInfoEntityDaoConfig.clearIdentityScope();
        deviceEntityDaoConfig.clearIdentityScope();
    }

    public UserInfoEntityDao getUserInfoEntityDao() {
        return userInfoEntityDao;
    }

    public DeviceEntityDao getDeviceEntityDao() {
        return deviceEntityDao;
    }

}