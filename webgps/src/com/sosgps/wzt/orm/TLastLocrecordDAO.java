package com.sosgps.wzt.orm;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class TLastLocrecord.
 * 
 * @see com.sosgps.wzt.orm.TLastLocrecord
 * @author MyEclipse Persistence Tools
 */

public class TLastLocrecordDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TLastLocrecordDAO.class);

	// property constants
	public static final String LATITUDE = "latitude";

	public static final String LONGITUDE = "longitude";

	public static final String JMX = "jmx";

	public static final String JMY = "jmy";

	public static final String SPEED = "speed";

	public static final String DIRECTION = "direction";

	public static final String HEIGHT = "height";

	public static final String DISTANCE = "distance";

	public static final String STATLLITE_NUM = "statlliteNum";

	public static final String DEVICE_ID = "deviceId";

	public static final String LOCATE_TYPE = "locateType";

	protected void initDao() {
		// do nothing
	}

	public void save(TLastLocrecord transientInstance) {
		log.debug("saving TLastLocrecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TLastLocrecord persistentInstance) {
		log.debug("deleting TLastLocrecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TLastLocrecord findById(java.lang.Long id) {
		log.debug("getting TLastLocrecord instance with id: " + id);
		try {
			TLastLocrecord instance = (TLastLocrecord) getHibernateTemplate()
					.get("com.sosgps.wzt.orm.TLastLocrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TLastLocrecord instance) {
		log.debug("finding TLastLocrecord instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TLastLocrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TLastLocrecord as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByJmx(Object jmx) {
		return findByProperty(JMX, jmx);
	}

	public List findByJmy(Object jmy) {
		return findByProperty(JMY, jmy);
	}

	public List findBySpeed(Object speed) {
		return findByProperty(SPEED, speed);
	}

	public List findByDirection(Object direction) {
		return findByProperty(DIRECTION, direction);
	}

	public List findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List findByDistance(Object distance) {
		return findByProperty(DISTANCE, distance);
	}

	public List findByStatlliteNum(Object statlliteNum) {
		return findByProperty(STATLLITE_NUM, statlliteNum);
	}

	public List findByDeviceId(Object deviceId) {
		return findByProperty(DEVICE_ID, deviceId);
	}

	public List findByLocateType(Object locateType) {
		return findByProperty(LOCATE_TYPE, locateType);
	}

	public List findAll() {
		log.debug("finding all TLastLocrecord instances");
		try {
			String queryString = "from TLastLocrecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TLastLocrecord merge(TLastLocrecord detachedInstance) {
		log.debug("merging TLastLocrecord instance");
		try {
			TLastLocrecord result = (TLastLocrecord) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TLastLocrecord instance) {
		log.debug("attaching dirty TLastLocrecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TLastLocrecord instance) {
		log.debug("attaching clean TLastLocrecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TLastLocrecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TLastLocrecordDAO) ctx.getBean("TLastLocrecordDAO");
	}
}