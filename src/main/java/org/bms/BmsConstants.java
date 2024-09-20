package org.bms;

public class BmsConstants {
    public static final String BMS_BASIC_API = "BMS basic API";
    public static final String BMS_COMMON_APIS_FOR_AUTHENTICATED_USERS = "BMS Common APIs for authenticated users";
    public static final String BMS_GUEST_API = "BMS guest API";
    public static final String BMS_GUEST_APIS_FOR_AUTHENTICATED_USERS = "API endpoints for guest users";

    public static final String MOCK_BMS_BASIC_API = "Mock BMS basic API";
    public static final String MOCK_BMS_COMMON_APIS_FOR_AUTHENTICATED_USERS = "Mock BMS Common APIs for authenticated users";


    // Example contents for OpenAPI definition
    public static final String EXAMPLE_TEAM_ID_RESPONSE = """
            {
              "id": 123,
              "name": "CJRDHNYW",
              "regulationAtBats": 9466.13924949258,
              "createdAt": "2020-12-11T11:14:01.474910848",
              "updatedAt": "2021-12-31T11:14:01.474910848",
              "url_path": "gvoel"
            }""";
    public static final String EXAMPLE_TEAMS_RESPONSE = """
            [
              {
                "id": 7935,
                "name": "OLYBTSMAJN",
                "regulationAtBats": 3314.7160749257064,
                "createdAt": "2021-02-16T20:17:23.077965358",
                "updatedAt": "2023-02-16T20:17:23.077965358",
                "deletedAt": null,
                "url_path": "xdlpa"
              },
              {
                "id": 7550,
                "name": "MHTPJMX",
                "regulationAtBats": 9285.838092713186,
                "createdAt": "2020-02-25T22:05:54.233017228",
                "updatedAt": "2022-02-24T22:05:54.233017228",
                "deletedAt": null,
                "url_path": "oalzynjxvmosca"
              },
              {
                "id": 1781,
                "name": "HYZCGXM",
                "regulationAtBats": 8751.063662102344,
                "createdAt": "2017-09-14T04:09:42.712983081",
                "updatedAt": "2019-09-14T04:09:42.712983081",
                "deletedAt": null,
                "url_path": "viql"
              },
              {
                "id": 813,
                "name": "MROXVVJIS",
                "regulationAtBats": 2237.6594648617574,
                "createdAt": "2015-08-22T01:26:03.278734206",
                "updatedAt": "2017-08-21T01:26:03.278734206",
                "deletedAt": null,
                "url_path": "ulohy"
              }
            ]""";
}
