package ru.appline.logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Util.ErrorText;
import ru.appline.logic.Util.JsonHelper;
import ru.appline.logic.Util.RequestReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/calculate")
public class ServletCalculator extends HttpServlet {

    Operation operation = Operation.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonHelper helper = JsonHelper.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jObj = gson.fromJson(String.valueOf(RequestReader.read(request)), JsonObject.class);
        PrintWriter pw = response.getWriter();

        request.setCharacterEncoding("UTF-8");

        String a = jObj.get("a").getAsString();
        String b = jObj.get("b").getAsString();
        String math = jObj.get("math").getAsString();

        response.setContentType("application/json;charset=utf-8");
        if (operation.checkData(a, b)) {
            if (operation.checkOperation(math)) {
                if (Double.parseDouble(b) == 0 && math.equals("/")) {
                    pw.println(gson.toJson(helper.getJsonHelper().getMessage(ErrorText.DIVISION_BY_ZERO)));
                } else {
                    pw.println(gson.toJson(helper.getJsonHelper()
                            .getResult(operation.getResult(math, Double.parseDouble(a), Double.parseDouble(b)))));
                }
            } else {
                pw.println(gson.toJson(helper.getJsonHelper().getMessage(ErrorText.NOT_FOUND_OPERATION)));
            }
        } else {
            pw.println(gson.toJson(helper.getJsonHelper().getMessage(ErrorText.INCORRECT_DATA)));
        }
    }
}
