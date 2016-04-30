package cn.water.cf.dao;

import java.util.List;

import cn.water.cf.domain.sns.Friendships;

public interface IFriendshipsDao extends ICommonDao<Friendships>{
	
	public static final String SERVICE_NAME ="cn.water.cf.dao.impl.FriendshipsDaoImpl";
	
	List<Object[]> findFriendsByUserId(String userId);

}
