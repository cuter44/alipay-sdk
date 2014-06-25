package com.github.cuter44.alipay.webinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

import com.github.cuter44.alipay.*;
import com.github.cuter44.alipay.resps.*;
import static com.github.cuter44.alipay.util.HttpParamParser.parseHttpParam;
import static com.github.cuter44.alipay.util.XMLParser.parseXML;

public class AlipayNotifyGatewayServlet extends HttpServlet
{
    protected static final String PROPKEY_NOTIFY_DATA = "notify_data";
    //private ServletContext context = this.getServletContext();

    protected AlipayNotifyPublisher gateway = new AlipayNotifyPublisher();

    // FOR TEST ONLY
    // QUOTE ME ON PRODUCE ENVIRONMENT
    //@Override
    //public void init()
    //{
        //this.gateway.addListener(
            //new AlipayNotifyListener(){
                //@Override
                //public boolean handle(NotifyBase n)
                //{

                    //System.out.println(n.getString());
                    //System.out.println(n.getProperties().toString());

                    //System.out.println("verify notify... "+n.verify(new AlipayFactory()));

                    //System.out.println("handled.");
                    //return(true);
                //}
            //}
        //);
    //}

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException
    {
        // Encoding Configuration
        // encode of req effects post method only
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // Dequote if pend to use session
        //HttpSession session = req.getSession();

	    // Dequote if pend to write binary
        //resp.setContentType("???MIME");
        //OutputStream out = resp.getOutputStream();

        // Dequote if pend to write chars
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();


        //String queryString = req.getQueryString();
        //Properties queryProp = parseHttpParam(queryString);
        Properties queryProp = new Properties();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            queryProp.setProperty(key, req.getParameter(key));
        }
        if (queryProp.containsKey(PROPKEY_NOTIFY_DATA))
            queryProp.putAll(parseXML(queryProp.getProperty(PROPKEY_NOTIFY_DATA)));

        // DUMP
        //System.out.println("catched");
        ////System.out.println(queryString);
        //System.out.println(queryProp.toString());

        NotifyBase n = new NotifyBase(null, queryProp);

        if (gateway.publish(n))
            out.print("success");

        return;
    }

    public void addListener(AlipayNotifyListener l)
    {
        this.gateway.addListener(l);

        return;
    }

    public void removeListener(AlipayNotifyListener l)
    {
        this.gateway.removeListener(l);

        return;
    }
}
