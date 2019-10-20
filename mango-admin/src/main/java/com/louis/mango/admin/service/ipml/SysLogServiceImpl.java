package com.louis.mango.admin.service.ipml;

import com.louis.mango.admin.dao.SysLogMapper;
import com.louis.mango.admin.model.SysLog;
import com.louis.mango.admin.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author ltt
 * @since 2019-10-19
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
