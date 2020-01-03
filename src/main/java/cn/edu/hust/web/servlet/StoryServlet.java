package cn.edu.hust.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/story/*")
public class StoryServlet extends BaseServlet {

    public void storyBuild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer("");
        try {
            InputStream is = StoryServlet.class.getClassLoader().getResourceAsStream("story.txt");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader in= new BufferedReader(isr);
            String line=null;
            while( (line=in.readLine())!=null ) {
                sb.append(line+='\n');
            }
            in.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeValue(response, sb.toString());
    }
}
