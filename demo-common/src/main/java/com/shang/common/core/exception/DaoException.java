package com.shang.common.core.exception;/**
 * Created by Think on 2017/11/26.
 */

import com.shang.common.core.enums.DataBaseExceptionEnum;


/**
 * Dao层异常
 *
 * @author NICK
 * @date 2017-11-26 16:32
 **/
public class DaoException  extends AppException {

    public DaoException(int code, String msgFormat, Object... args) {
        super(code,msgFormat,args);
    }

    public DaoException(DataBaseExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }


    /**
     * 实例化异常
     * @param msgFormat
     * @param args
     * @return
     */
    private DaoException newInstance(String msgFormat, Object... args) {
        return new DaoException(this.code, msgFormat, args);
    }

    /**
     * 数据库操作,insert返回0
     */
    public static final DaoException DB_INSERT_RESULT_0 = new DaoException(DataBaseExceptionEnum.DB_INSERT_RESULT_0);

    /**
     * 数据库操作,update返回0
     */
    public static final DaoException DB_UPDATE_RESULT_0 = new DaoException(DataBaseExceptionEnum.DB_UPDATE_RESULT_0);

    /**
     * 数据库操作,selectOne返回null
     */
    public static final DaoException DB_SELECTONE_IS_NULL = new DaoException(DataBaseExceptionEnum.DB_SELECTONE_IS_NULL);

    /**
     * 数据库操作,list返回null
     */
    public static final DaoException DB_LIST_IS_NULL = new DaoException(DataBaseExceptionEnum.DB_LIST_IS_NULL);

}
