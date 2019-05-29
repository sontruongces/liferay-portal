/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.TeamProjectRole;
import com.liferay.osb.koroneiki.taproot.model.TeamProjectRoleModel;
import com.liferay.osb.koroneiki.taproot.model.TeamProjectRoleSoap;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamProjectRolePK;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the TeamProjectRole service. Represents a row in the &quot;Koroneiki_TeamProjectRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>TeamProjectRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link TeamProjectRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamProjectRoleImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class TeamProjectRoleModelImpl
	extends BaseModelImpl<TeamProjectRole> implements TeamProjectRoleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a team project role model instance should use the <code>TeamProjectRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_TeamProjectRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"teamId", Types.BIGINT}, {"projectId", Types.BIGINT},
		{"teamRoleId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("teamId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("projectId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("teamRoleId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_TeamProjectRole (teamId LONG not null,projectId LONG not null,teamRoleId LONG not null,primary key (teamId, projectId, teamRoleId))";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_TeamProjectRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY teamProjectRole.id.teamId ASC, teamProjectRole.id.projectId ASC, teamProjectRole.id.teamRoleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_TeamProjectRole.teamId ASC, Koroneiki_TeamProjectRole.projectId ASC, Koroneiki_TeamProjectRole.teamRoleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long PROJECTID_COLUMN_BITMASK = 1L;

	public static final long TEAMID_COLUMN_BITMASK = 2L;

	public static final long TEAMROLEID_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static TeamProjectRole toModel(TeamProjectRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		TeamProjectRole model = new TeamProjectRoleImpl();

		model.setTeamId(soapModel.getTeamId());
		model.setProjectId(soapModel.getProjectId());
		model.setTeamRoleId(soapModel.getTeamRoleId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<TeamProjectRole> toModels(
		TeamProjectRoleSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<TeamProjectRole> models = new ArrayList<TeamProjectRole>(
			soapModels.length);

		for (TeamProjectRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public TeamProjectRoleModelImpl() {
	}

	@Override
	public TeamProjectRolePK getPrimaryKey() {
		return new TeamProjectRolePK(_teamId, _projectId, _teamRoleId);
	}

	@Override
	public void setPrimaryKey(TeamProjectRolePK primaryKey) {
		setTeamId(primaryKey.teamId);
		setProjectId(primaryKey.projectId);
		setTeamRoleId(primaryKey.teamRoleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new TeamProjectRolePK(_teamId, _projectId, _teamRoleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((TeamProjectRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return TeamProjectRole.class;
	}

	@Override
	public String getModelClassName() {
		return TeamProjectRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<TeamProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<TeamProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((TeamProjectRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<TeamProjectRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<TeamProjectRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(TeamProjectRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<TeamProjectRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<TeamProjectRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, TeamProjectRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			TeamProjectRole.class.getClassLoader(), TeamProjectRole.class,
			ModelWrapper.class);

		try {
			Constructor<TeamProjectRole> constructor =
				(Constructor<TeamProjectRole>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<TeamProjectRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<TeamProjectRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<TeamProjectRole, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<TeamProjectRole, Object>>();
		Map<String, BiConsumer<TeamProjectRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<TeamProjectRole, ?>>();

		attributeGetterFunctions.put("teamId", TeamProjectRole::getTeamId);
		attributeSetterBiConsumers.put(
			"teamId",
			(BiConsumer<TeamProjectRole, Long>)TeamProjectRole::setTeamId);
		attributeGetterFunctions.put(
			"projectId", TeamProjectRole::getProjectId);
		attributeSetterBiConsumers.put(
			"projectId",
			(BiConsumer<TeamProjectRole, Long>)TeamProjectRole::setProjectId);
		attributeGetterFunctions.put(
			"teamRoleId", TeamProjectRole::getTeamRoleId);
		attributeSetterBiConsumers.put(
			"teamRoleId",
			(BiConsumer<TeamProjectRole, Long>)TeamProjectRole::setTeamRoleId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getTeamId() {
		return _teamId;
	}

	@Override
	public void setTeamId(long teamId) {
		_columnBitmask |= TEAMID_COLUMN_BITMASK;

		if (!_setOriginalTeamId) {
			_setOriginalTeamId = true;

			_originalTeamId = _teamId;
		}

		_teamId = teamId;
	}

	public long getOriginalTeamId() {
		return _originalTeamId;
	}

	@JSON
	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_columnBitmask |= PROJECTID_COLUMN_BITMASK;

		if (!_setOriginalProjectId) {
			_setOriginalProjectId = true;

			_originalProjectId = _projectId;
		}

		_projectId = projectId;
	}

	public long getOriginalProjectId() {
		return _originalProjectId;
	}

	@JSON
	@Override
	public long getTeamRoleId() {
		return _teamRoleId;
	}

	@Override
	public void setTeamRoleId(long teamRoleId) {
		_columnBitmask |= TEAMROLEID_COLUMN_BITMASK;

		if (!_setOriginalTeamRoleId) {
			_setOriginalTeamRoleId = true;

			_originalTeamRoleId = _teamRoleId;
		}

		_teamRoleId = teamRoleId;
	}

	public long getOriginalTeamRoleId() {
		return _originalTeamRoleId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public TeamProjectRole toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		TeamProjectRoleImpl teamProjectRoleImpl = new TeamProjectRoleImpl();

		teamProjectRoleImpl.setTeamId(getTeamId());
		teamProjectRoleImpl.setProjectId(getProjectId());
		teamProjectRoleImpl.setTeamRoleId(getTeamRoleId());

		teamProjectRoleImpl.resetOriginalValues();

		return teamProjectRoleImpl;
	}

	@Override
	public int compareTo(TeamProjectRole teamProjectRole) {
		TeamProjectRolePK primaryKey = teamProjectRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TeamProjectRole)) {
			return false;
		}

		TeamProjectRole teamProjectRole = (TeamProjectRole)obj;

		TeamProjectRolePK primaryKey = teamProjectRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		TeamProjectRoleModelImpl teamProjectRoleModelImpl = this;

		teamProjectRoleModelImpl._originalTeamId =
			teamProjectRoleModelImpl._teamId;

		teamProjectRoleModelImpl._setOriginalTeamId = false;

		teamProjectRoleModelImpl._originalProjectId =
			teamProjectRoleModelImpl._projectId;

		teamProjectRoleModelImpl._setOriginalProjectId = false;

		teamProjectRoleModelImpl._originalTeamRoleId =
			teamProjectRoleModelImpl._teamRoleId;

		teamProjectRoleModelImpl._setOriginalTeamRoleId = false;

		teamProjectRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<TeamProjectRole> toCacheModel() {
		TeamProjectRoleCacheModel teamProjectRoleCacheModel =
			new TeamProjectRoleCacheModel();

		teamProjectRoleCacheModel.teamProjectRolePK = getPrimaryKey();

		teamProjectRoleCacheModel.teamId = getTeamId();

		teamProjectRoleCacheModel.projectId = getProjectId();

		teamProjectRoleCacheModel.teamRoleId = getTeamRoleId();

		return teamProjectRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<TeamProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<TeamProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((TeamProjectRole)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<TeamProjectRole, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<TeamProjectRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<TeamProjectRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((TeamProjectRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, TeamProjectRole>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _teamId;
	private long _originalTeamId;
	private boolean _setOriginalTeamId;
	private long _projectId;
	private long _originalProjectId;
	private boolean _setOriginalProjectId;
	private long _teamRoleId;
	private long _originalTeamRoleId;
	private boolean _setOriginalTeamRoleId;
	private long _columnBitmask;
	private TeamProjectRole _escapedModel;

}