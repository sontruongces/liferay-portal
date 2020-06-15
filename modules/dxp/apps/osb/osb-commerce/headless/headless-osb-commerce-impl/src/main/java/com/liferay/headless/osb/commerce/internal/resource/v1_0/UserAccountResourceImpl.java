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

package com.liferay.headless.osb.commerce.internal.resource.v1_0;

import com.liferay.headless.osb.commerce.dto.v1_0.UserAccount;
import com.liferay.headless.osb.commerce.resource.v1_0.UserAccountResource;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Ivica Cardic
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/user-account.properties",
	scope = ServiceScope.PROTOTYPE, service = UserAccountResource.class
)
public class UserAccountResourceImpl extends BaseUserAccountResourceImpl {

	@Override
	public UserAccount postUserAccount(UserAccount userAccount)
		throws Exception {

		User user = _userService.addUser(
			contextCompany.getCompanyId(), true, null, null, false,
			userAccount.getScreenName(), userAccount.getEmailAddress(), 0, null,
			LocaleUtil.fromLanguageId(userAccount.getLanguageId()),
			userAccount.getFirstName(), userAccount.getMiddleName(),
			userAccount.getLastName(), 0, 0, true,
			_getBirthdayMonth(userAccount), _getBirthdayDay(userAccount),
			_getBirthdayYear(userAccount), userAccount.getJobTitle(),
			new long[0], new long[0], new long[0], new long[0],
			Collections.emptyList(), Collections.emptyList(),
			Collections.emptyList(), Collections.emptyList(),
			Collections.emptyList(), false,
			ServiceContextFactory.getInstance(contextHttpServletRequest));

		_userLocalService.updatePasswordManually(
			user.getUserId(), userAccount.getPassword(), true, false,
			new Date());

		_addUserSiteOwnerGroupRole(user.getUserId());

		_addUserOSBCommerceAdministratorRole(user.getUserId());

		return _toUserAccount(user);
	}

	private void _addUserOSBCommerceAdministratorRole(long userId)
		throws PortalException {

		Role role = _roleService.getRole(
			contextCompany.getCompanyId(), "OSB Commerce Administrator");

		_roleService.addUserRoles(userId, new long[] {role.getRoleId()});
	}

	private void _addUserSiteOwnerGroupRole(long userId)
		throws PortalException {

		Role role = _roleService.getRole(
			contextCompany.getCompanyId(), RoleConstants.SITE_OWNER);

		Group group = _groupLocalService.getFriendlyURLGroup(
			contextCompany.getCompanyId(), "/osb-commerce");

		_userGroupRoleService.addUserGroupRoles(
			userId, group.getGroupId(), new long[] {role.getRoleId()});
	}

	private int _getBirthdayDay(UserAccount userAccount) {
		return _getCalendarFieldValue(userAccount, Calendar.DAY_OF_MONTH, 1);
	}

	private int _getBirthdayMonth(UserAccount userAccount) {
		return _getCalendarFieldValue(
			userAccount, Calendar.MONTH, Calendar.JANUARY);
	}

	private int _getBirthdayYear(UserAccount userAccount) {
		return _getCalendarFieldValue(userAccount, Calendar.YEAR, 1977);
	}

	private int _getCalendarFieldValue(
		UserAccount userAccount, int calendarField, int defaultValue) {

		return Optional.ofNullable(
			userAccount.getBirthDate()
		).map(
			date -> {
				Calendar calendar = CalendarFactoryUtil.getCalendar();

				calendar.setTime(date);

				return calendar.get(calendarField);
			}
		).orElse(
			defaultValue
		);
	}

	private UserAccount _toUserAccount(User user) throws Exception {
		return new UserAccount() {
			{
				birthDate = user.getBirthday();
				dateCreated = user.getCreateDate();
				dateModified = user.getModifiedDate();
				emailAddress = user.getEmailAddress();
				firstName = user.getFirstName();
				id = user.getUserId();
				jobTitle = user.getJobTitle();
				lastName = user.getLastName();
				male = user.getMale();
				middleName = user.getMiddleName();
				name = user.getFullName();
				screenName = user.getScreenName();
			}
		};
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleService _roleService;

	@Reference
	private UserGroupRoleService _userGroupRoleService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserService _userService;

}