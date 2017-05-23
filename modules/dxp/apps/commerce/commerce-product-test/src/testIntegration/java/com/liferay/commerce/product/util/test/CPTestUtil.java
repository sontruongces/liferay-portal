/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.util.test;

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalServiceUtil;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalServiceUtil;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelLocalServiceUtil;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionCategoryLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionValueLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowThreadLocal;
import com.liferay.portal.test.randomizerbumpers.TikaSafeRandomizerBumper;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * @author Andrea Di Giorgi
 */
public class CPTestUtil {

	public static CPAttachmentFileEntry addCPAttachmentFileEntry(
			long groupId, long classNameId, long classPK, long fileEntryId,
			List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

		long now = System.currentTimeMillis();

		Date displayDate = new Date(now - Time.HOUR);
		Date expirationDate = new Date(now + Time.DAY);

		Calendar displayCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		displayCal.setTime(displayDate);

		int displayDateMonth = displayCal.get(Calendar.MONTH);
		int displayDateDay = displayCal.get(Calendar.DATE);
		int displayDateYear = displayCal.get(Calendar.YEAR);
		int displayDateHour = displayCal.get(Calendar.HOUR);
		int displayDateMinute = displayCal.get(Calendar.MINUTE);

		if (displayCal.get(Calendar.AM_PM) == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		expirationCal.setTime(expirationDate);

		int expirationDateMonth = expirationCal.get(Calendar.MONTH);
		int expirationDateDay = expirationCal.get(Calendar.DATE);
		int expirationDateYear = expirationCal.get(Calendar.YEAR);
		int expirationDateHour = expirationCal.get(Calendar.HOUR);
		int expirationDateMinute = expirationCal.get(Calendar.MINUTE);

		if (expirationCal.get(Calendar.AM_PM) == Calendar.PM) {
			expirationDateHour += 12;
		}

		String json = getJSON(cpDefinitionOptionValueRels);

		return CPAttachmentFileEntryLocalServiceUtil.addCPAttachmentFileEntry(
			classNameId, classPK, fileEntryId, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, false, json,
			RandomTestUtil.randomInt(), 0, serviceContext);
	}

	public static CPDefinition addCPDefinition(long groupId) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return addCPDefinition(serviceContext);
	}

	public static CPDefinitionOptionRel addCPDefinitionOptionRel(
			long groupId, long cpDefinitionId, long cpOptionId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CPDefinitionOptionRelLocalServiceUtil.addCPDefinitionOptionRel(
			cpDefinitionId, cpOptionId, RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null,
			RandomTestUtil.randomInt(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean(), RandomTestUtil.randomBoolean(),
			false, serviceContext);
	}

	public static CPDefinitionOptionValueRel addCPDefinitionOptionValueRel(
			long groupId, long cpDefinitionOptionRelId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CPDefinitionOptionValueRelLocalServiceUtil.
			addCPDefinitionOptionValueRel(
				cpDefinitionOptionRelId, RandomTestUtil.randomString(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomInt(), serviceContext);
	}

	public static CPDefinition addCPDefinitionWithWorkflow(
			long groupId, boolean approved)
		throws Exception {

		boolean workflowEnabled = WorkflowThreadLocal.isEnabled();

		try {
			WorkflowThreadLocal.setEnabled(true);

			ServiceContext serviceContext =
				ServiceContextTestUtil.getServiceContext(groupId);

			serviceContext = (ServiceContext)serviceContext.clone();

			serviceContext.setWorkflowAction(
				WorkflowConstants.ACTION_SAVE_DRAFT);

			CPDefinition cpDefinition = addCPDefinition(serviceContext);

			if (approved) {
				return updateStatus(cpDefinition, serviceContext);
			}

			return cpDefinition;
		}
		finally {
			WorkflowThreadLocal.setEnabled(workflowEnabled);
		}
	}

	public static CPInstance addCPInstance(
			long groupId, long cpDefinitionId,
			List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels)
		throws Exception {

		String ddmContent = getJSON(cpDefinitionOptionValueRels);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

		long now = System.currentTimeMillis();

		Date displayDate = new Date(now - Time.HOUR);
		Date expirationDate = new Date(now + Time.DAY);

		Calendar displayCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		displayCal.setTime(displayDate);

		int displayDateMonth = displayCal.get(Calendar.MONTH);
		int displayDateDay = displayCal.get(Calendar.DATE);
		int displayDateYear = displayCal.get(Calendar.YEAR);
		int displayDateHour = displayCal.get(Calendar.HOUR);
		int displayDateMinute = displayCal.get(Calendar.MINUTE);

		if (displayCal.get(Calendar.AM_PM) == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		expirationCal.setTime(expirationDate);

		int expirationDateMonth = expirationCal.get(Calendar.MONTH);
		int expirationDateDay = expirationCal.get(Calendar.DATE);
		int expirationDateYear = expirationCal.get(Calendar.YEAR);
		int expirationDateHour = expirationCal.get(Calendar.HOUR);
		int expirationDateMinute = expirationCal.get(Calendar.MINUTE);

		if (expirationCal.get(Calendar.AM_PM) == Calendar.PM) {
			expirationDateHour += 12;
		}

		return CPInstanceLocalServiceUtil.addCPInstance(
			cpDefinitionId, RandomTestUtil.randomString(), ddmContent,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, false,
			serviceContext);
	}

	public static CPOption addCPOption(long groupId) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CPOptionLocalServiceUtil.addCPOption(
			RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null,
			RandomTestUtil.randomBoolean(), RandomTestUtil.randomBoolean(),
			RandomTestUtil.randomBoolean(), serviceContext);
	}

	public static CPOptionCategory addCPOptionCategory(long groupId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CPOptionCategoryLocalServiceUtil.addCPOptionCategory(
			RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), RandomTestUtil.randomInt(),
			serviceContext);
	}

	public static CPOptionValue addCPOptionValue(long groupId, long cpOptionId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CPOptionValueLocalServiceUtil.addCPOptionValue(
			cpOptionId, RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(), RandomTestUtil.randomInt(),
			serviceContext);
	}

	public static FileEntry addFileEntry(long groupId) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return DLAppLocalServiceUtil.addFileEntry(
			serviceContext.getUserId(), groupId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString() + ".txt", ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomBytes(TikaSafeRandomizerBumper.INSTANCE),
			serviceContext);
	}

	public static void assertDateEquals(Date date1, Date date2) {
		Calendar calendar1 = null;
		Calendar calendar2 = null;

		if (date1 != null) {
			calendar1 = Calendar.getInstance();

			calendar1.setTime(date1);

			calendar1.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
		}

		if (date2 != null) {
			calendar2 = Calendar.getInstance();

			calendar2.setTime(date2);

			calendar2.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
		}

		Assert.assertEquals(calendar1, calendar2);
	}

	protected static CPDefinition addCPDefinition(ServiceContext serviceContext)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

		long now = System.currentTimeMillis();

		Date displayDate = new Date(now - Time.HOUR);
		Date expirationDate = new Date(now + Time.DAY);

		Calendar displayCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		displayCal.setTime(displayDate);

		int displayDateMonth = displayCal.get(Calendar.MONTH);
		int displayDateDay = displayCal.get(Calendar.DATE);
		int displayDateYear = displayCal.get(Calendar.YEAR);
		int displayDateHour = displayCal.get(Calendar.HOUR);
		int displayDateMinute = displayCal.get(Calendar.MINUTE);

		if (displayCal.get(Calendar.AM_PM) == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCal = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		expirationCal.setTime(expirationDate);

		int expirationDateMonth = expirationCal.get(Calendar.MONTH);
		int expirationDateDay = expirationCal.get(Calendar.DATE);
		int expirationDateYear = expirationCal.get(Calendar.YEAR);
		int expirationDateHour = expirationCal.get(Calendar.HOUR);
		int expirationDateMinute = expirationCal.get(Calendar.MINUTE);

		if (expirationCal.get(Calendar.AM_PM) == Calendar.PM) {
			expirationDateHour += 12;
		}

		return CPDefinitionLocalServiceUtil.addCPDefinition(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null, null,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, false,
			serviceContext);
	}

	protected static String getJSON(
		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (CPDefinitionOptionValueRel cpDefinitionOptionValueRel :
				cpDefinitionOptionValueRels) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"cpDefinitionOptionRelId",
				cpDefinitionOptionValueRel.getCPDefinitionOptionRelId());
			jsonObject.put(
				"cpDefinitionOptionValueRelId",
				cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId());

			jsonArray.put(jsonObject);
		}

		return jsonArray.toJSONString();
	}

	protected static CPDefinition updateStatus(
			CPDefinition cpDefinition, ServiceContext serviceContext)
		throws Exception {

		Map<String, Serializable> workflowContext = new HashMap<>();

		workflowContext.put(WorkflowConstants.CONTEXT_URL, "http://localhost");
		workflowContext.put(
			WorkflowConstants.CONTEXT_USER_PORTRAIT_URL, "http://localhost");
		workflowContext.put(
			WorkflowConstants.CONTEXT_USER_URL, "http://localhost");

		return CPDefinitionLocalServiceUtil.updateStatus(
			cpDefinition.getUserId(), cpDefinition.getCPDefinitionId(),
			WorkflowConstants.STATUS_APPROVED, serviceContext, workflowContext);
	}

}