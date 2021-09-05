package com.org.tests;
import com.org.client.RestClient;
import com.org.main.TestMain;
import com.org.util.TestUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class APITest extends TestMain {
    TestMain testMain;
    String url;
    CloseableHttpResponse closableHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testMain = new TestMain();
        url = prop.getProperty("URL");
        RestClient restClient = new RestClient();
        restClient.get(url);
    }

    @Test (priority = 0)
    public void getAllRepo() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("searchAllRepo")); //This url will give all the available Repositories

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode +" OK");

        //Verify the status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify the JSON response (with total repositories count, which is a dynamic number and the TC can fail)
        String totalRepos = TestUtil.getValueByJPath(responseJson,"total_count");
        System.out.println("Total count of git hub repositories -"+totalRepos);
        Assert.assertEquals(Integer.parseInt(totalRepos),643292,"Total count changed as this is a dynamic value");

    }
    @Test (priority = 1)
    public void getRepoByLanguage() throws IOException {
      RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("searchRepoByLanguage")); //This url will return repositories with language Java and starred from lowest to highest

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode +" OK");

        //Verify status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify JSON response (with specified language - Java)
        String language = TestUtil.getValueByJPath(responseJson,"items[0]language");
        System.out.println("Expected language is -"+language);
        Assert.assertEquals(language,"Java");
    }
    @Test (priority = 2)
    public void getRepoByDate() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("searchRepoByDate")); //This url will return the repositories for the specified date

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode +" OK");

        //Verify status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify JSON response (with specified date)
        String date = TestUtil.getValueByJPath(responseJson,"items[0]created_at");
        System.out.println("Expected date is -"+date);
        Assert.assertEquals(date,"2018-12-02T16:18:16Z");
    }
    @Test (priority = 3)
    public void getRepoByTwoLanguages() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url + prop.getProperty("searchRepoByTwoLanguages")); //This url will return all the repositories having language Python and Java

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - " + statusCode + " OK");

        //Verify status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -" + responseJson);

        //Verify JSON response (with specified languages - Python & Java)
        String languageP = TestUtil.getValueByJPath(responseJson, "items[0]language");
        System.out.println("Expected language is -" + languageP);
        if ("Python".equals(languageP)){
        Assert.assertEquals(languageP, "Python");
        }
        else{
        Assert.assertEquals(languageP, "Java");
        }
        System.out.println("Actual language is -" + languageP);
    }
    @Test (priority = 4)
    public void getRepoByUser() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("searchRepoByUser")); //This url will return all the repositories for the specified user

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode +" OK");

        //Verify status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify JSON response (with specified user)
        String user = TestUtil.getValueByJPath(responseJson,"items[0]full_name");
        System.out.println("Expected user is -"+user);
        Assert.assertEquals(user,"defunkt/jquery-pjax");
    }
    @Test (priority = 5)
    public void getStarredRepo() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("searchStarredRepoSortDesc"));// This url will return all the repositories which are starred and sorting it by highest to lowest

        //Get status code from the response
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode +" OK");

        //Verify status code (200)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify JSON response (with the most starred user)
        String mostStarredUser = TestUtil.getValueByJPath(responseJson,"items[0]name");
        System.out.println("Expected user is -"+mostStarredUser);
        Assert.assertEquals(mostStarredUser,"SpringAll");
    }

    @Test (priority = 6)
    public void dataNotFound() throws IOException {
        RestClient restClient = new RestClient();
        closableHttpResponse = restClient.get(url+ prop.getProperty("noDataAvailable")); //This url is incorrect an provide no data with error code 422

        //Get status code
        int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is - "+statusCode);

        //Verify status code (422)
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_422, "Status code is not 422");

        //Get JSON response
        String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response from API -"+responseJson);

        //Verify JSON response (with the error message)
        String errorMessage = TestUtil.getValueByJPath(responseJson,"message");
        System.out.println("Expected message is -"+errorMessage);
        Assert.assertEquals(errorMessage,"Validation Failed");
    }

}
