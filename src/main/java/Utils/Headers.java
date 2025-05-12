package Utils;

import java.util.HashMap;

public class Headers {

    public HashMap generalHeader(){
      HashMap<String,String> header =new HashMap<>();
      header.put("Accept","application/json");
      header.put("Content-Type","application/json");
      return header;
    }

    public HashMap generalHeaderWithAuth(){
        HashMap<String,String> header =new HashMap<>();
        header.put("Accept","application/json");
        header.put("Content-Type","application/json");
        header.put("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        return header;
    }

}
