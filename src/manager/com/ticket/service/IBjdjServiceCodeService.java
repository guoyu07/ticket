package com.ticket.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;


/**
 * 服务码表业务接口
 * @ClassName: IBjdjServiceCodeService   
 * @Description: 提供服务码表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-23 15:16:42
 *
 */
public interface IBjdjServiceCodeService extends IBaseService<BjdjServiceCode, String> {
	
	/**
	 * 保存服务码表实体
	 * @Title: IBjdjServiceCodeService
	 * @Description:
	 * @param code  服务码
	 * @param type_id  服务码类型ID（字典）
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	boolean persist(String code,SystemDictionary type, SystemDictionary state, String versionFlag) throws ServiceException;
	
	/**
	 * 修改服务码表实体
	 * @Title: IBjdjServiceCodeService
	 * @Description:
	 * @param code  服务码
	 * @param type_id  服务码类型ID（字典）
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean merge(String id,SystemDictionary type, SystemDictionary state, String versionFlag) throws ServiceException;
	
	/**
	 * 移除服务码表实体
	 * @Title: IBjdjServiceCodeService
	 * @Description: 
	 * @param id 服务码表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：得到没有使用的指定类型，指定数量的服务码
	 * @param type
	 * @return
	 */
	List<BjdjServiceCode> getNotUsedByType(String type, int count);
	
	/**
	 * @Description：通过服务码字符串得到服务码对象
	 * @param code
	 * @return
	 */
	BjdjServiceCode getByCode(String code);
	
	/**
	 * @Description：得到最大的服务码中间的字母（中间的几位字母最大的服务码）
	 * @return
	 */
	String getMaxCode();
	
	/**
	 * @Description：通过列和列值，查询一批对象
	 * @param column
	 * @param value
	 * @return
	 */
	List<BjdjServiceCode> queryByColumn(String column, Object value);
	
	/**
	 * @Description：分页查询
	 * @param type_id
	 * @param state_id
	 * @param startTime
	 * @param endTime
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 */
	Pagination queryEntityByAdmin(String start, String end, String type_id, String state_id, Date startTime, Date endTime, String versionFlag, Integer pageSize) throws ServiceException ;
	
	/**
	 * @Description：回收一个服务码
	 * @param serviceCode
	 * @param dictionaries
	 */
	void recycle(BjdjServiceCode serviceCode, SystemDictionary...dictionaries);
	
	/**
	 * @Description：回收一批服务码
	 * @param serviceCode
	 * @param dictionaries
	 */
	void recycle(List<BjdjServiceCode> serviceCodes, SystemDictionary...dictionaries);

	/**
	 * @Description：收到的别人赠送的服务码
	 * @param member
	 * @return
	 */
	List<BjdjServiceCode> receivedDonation(Member member);

	/**
	 * @Description：检测是否过期
	 */
	void checkExpire() throws ServiceException;
	
	/**
	 * @Description：是否可以激活
	 * @param id 服务码ID
	 * @return
	 */
	boolean canActivate(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以激活
	 * @param serviceCode
	 * @return
	 */
	boolean canActivate(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以激活
	 * @param serviceCode
	 * @param flightDate 起飞时间
	 * @return
	 */
	boolean canActivate(BjdjServiceCode serviceCode, Date flightDate) throws ServiceException;
	
	/**
	 * @Description：是否可以验证
	 * @param id 服务码ID
	 * @return
	 */
	boolean canValidate(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以验证
	 * @param serviceCode
	 * @return
	 */
	boolean canValidate(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以分单
	 * @param id 服务码ID
	 * @return
	 */
	boolean canDispatch(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以分单
	 * @param serviceCode
	 * @return
	 */
	boolean canDispatch(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以转赠
	 * @param id 服务码ID
	 * @return
	 */
	boolean canDonate(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以转赠
	 * @param serviceCode
	 * @return
	 */
	boolean canDonate(BjdjServiceCode serviceCode) throws ServiceException;
	/**
	 * 是否可以转赠（后台）
	 * @param serviceCode
	 * @return
	 * @throws ServiceException
	 */
	boolean canDonate1(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以评论
	 * @param id 服务码ID
	 * @return
	 */
	boolean canComment(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以评论
	 * @param serviceCode
	 * @return
	 */
	boolean canComment(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以延期
	 * @param id 服务码ID
	 * @return
	 */
	boolean canDelay(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以延期
	 * @param serviceCode
	 * @return
	 */
	boolean canDelay(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：是否可以删除
	 * @param id 服务码ID
	 * @return
	 */
	boolean canDelete(String id) throws ServiceException;
	
	/**
	 * @Description：是否可以删除
	 * @param serviceCode
	 * @return
	 */
	boolean canDelete(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * @Description：检测是否为登录用户的服务码
	 * @return
	 */
	boolean isMine(String id) throws ServiceException;
	
	/**
	 * @Description：检测是否登录用户的服务码
	 * @return
	 */
	boolean isMine(BjdjServiceCode serviceCode) throws ServiceException;
	
	/**
	 * 通过电话号码查询得到用户的所有
	 * @param mobile
	 * @return
	 */
	List<BjdjServiceCode> queryByMobile(String mobile);
	
	/**
	 * 通过电话号码查询得到用户的所有
	 * @param mobile
	 * @return
	 */
	List<BjdjServiceCode> queryByMobileUser(String mobile);
	/**
	 * @Description：查询登录用户的所有可以激活的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryMyCanActivates();
	
	/**
	 * @Description：查询登录用户的所有可以激活的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryCanActivates(String mid);
	
	/**
	 * @Description：查询登录用户的所有可以转赠的服务码
	 * @return
	 */
	List<BjdjServiceCode> queryMyCanDonates();
	
	/**
	 * 按规则批量生成指定个数的服务码
	 * @param number
	 * @param type_id 渠道类型
	 * @return
	 */
	void batchGenerate(int number, String type_id) throws ServiceException;
	
	/**
	 * @author wangjiafeng
	 * 激活服务码
	 * @method activateBjdjServiceCoede
	 * @param bjdjServiceCodeId
	 * @param memberId
	 * @param useDate
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-19 下午02:28:34
	 */
	String activateBjdjServiceCoede(String bjdjServiceCodeId,
			String memberId,String useDate,String flightNo) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 我要激活
	 * @method iWillActivate
	 * @param channelCustomerInfoLoginId
	 * @param idCard
	 * @param flightNo
	 * @param useTime
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-19 下午02:32:40
	 */
	String  iWillActivate(String channelCustomerInfoLoginId,
			String idCard,String flightNo,String useTime) throws ServiceException;
	
	/**
	 * 冻结服务码
	 * @param bjdjServiceCodes
	 * @return
	 * @throws ServiceException
	 */
	void froze(List<BjdjServiceCode> bjdjServiceCodes) throws ServiceException;
	
	/**
	 * 冻结服务码
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	void froze(String ids) throws ServiceException;
	
	/**
	 * 解冻结的服务码
	 * @param bjdjServiceCodes
	 * @return
	 * @throws ServiceException
	 */
	void unFroze(List<BjdjServiceCode> bjdjServiceCodes) throws ServiceException;
	
	/**
	 * 解冻结的服务码
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	void unFroze(String ids) throws ServiceException;

	/**
	 * 生成服务码数据报表
	 * @param out
	 * @param startTime
	 * @param endTime
	 */
	void generateReport(OutputStream out);
	
	/**
	 * 回收服务码（比如：订单退款、删除时）
	 * @param serviceCodes
	 */
	void recover(List<BjdjServiceCode> serviceCodes) throws ServiceException;
	
	/**
	 * 随机获取一个可以激活的服务码
	 * @param member_id 指定用户(不可为空)
	 * @param order_id 优先考虑指定订单中的服务码(可为空)
	 * @param serviceCodes 排除指定的这些服务码(可为空)
	 * @return
	 */
	BjdjServiceCode randomGetCanActivate(String member_id, String order_id, String[] codes);
	
	/**
	 * 保存(或者更新)一个服务码生成规则
	 * @param type_id 渠道类型
	 * @param rule
	 * @throws ServiceException
	 */
	void saveOrUpdateRule(String type_id, char[] rule) throws ServiceException;
	
	/**
	 * 获取服务码规则
	 * @param type_id 渠道类型
	 * @return
	 */
	char[] getRule(String type_id);
	
	/**
	 * 查询已经生成所有的服务码，按照指定的规则
	 * @param rule
	 * @return
	 */
	List<String> queryByRule(char[] rule);
}