/*
 * Copyright 2013-2016 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.demo.api.GetUserMethods;
import com.qaprosoft.carina.core.demo.api.PostUserMethod;
import com.qaprosoft.carina.core.foundation.APITest;
import com.qaprosoft.carina.core.foundation.http.HttpResponseStatusType;

/**
 * This sample shows how create web test.
 * 
 * @author Alex Khursevich
 */
public class APISampleTest extends APITest
{
	@Test
	public void testCreateUser()
	{
		PostUserMethod api = new PostUserMethod();
		api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		api.callAPI();
		api.validateResponse();
	}

	@Test
	public void testCreateUserMissingSomeFields()
	{
		PostUserMethod api = new PostUserMethod();
		api.getProperties().remove("name");
		api.getProperties().remove("username");
		api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
		api.callAPI();
		api.validateResponse();
	}

	@Test
	public void testGetUsers()
	{
		GetUserMethods getUsersMethods = new GetUserMethods();
		getUsersMethods.expectResponseStatus(HttpResponseStatusType.OK_200);
		getUsersMethods.callAPI();
		getUsersMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
		getUsersMethods.validateResponseAgainstJSONSchema("api/users/_get/rs.schema");
	}
}
