package adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility;

public class ConnectionUrl {

    private static String BASE_URL = "http://bukanjaknote.site/";

    public static String LOGIN = BASE_URL + "api/login";
    public static String REGISTER = BASE_URL + "api/register";
    public static String ADD_TO_CART = BASE_URL + "api/order/cart/add";
    public static String DETAIL_CART = BASE_URL + "api/order/cart/detail";
    public static String UPDATE_CART = BASE_URL + "api/order/cart/edit";
    public static String GET_PROFILE = BASE_URL + "api/user/profile";
    public static String RESET_CART = BASE_URL + "";
    public static String REMOVE_CART = BASE_URL + "";
    public static String UPDATE_PROFILE = BASE_URL + "api/user/profile/{id}";

}
