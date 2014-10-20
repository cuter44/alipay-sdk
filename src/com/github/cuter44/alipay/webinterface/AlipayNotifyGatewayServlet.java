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
    protected static final String KEY_NOTIFY_DATA = "notify_data";
    //private ServletContext context = this.getServletContext();

    protected AlipayNotifyPublisher gateway = new AlipayNotifyPublisher();

    // FOR TEST ONLY
    // QUOTE ME ON PRODUCE ENVIRONMENT
    @Override
    public void init(ServletConfig config)
    {
        if (Boolean.valueOf(config.getInitParameter("com.github.cuter44.alipay.notifygateway.dump")))
        {
            this.gateway.addListener(
                new AlipayNotifyListener(){
                    @Override
                    public boolean handle(NotifyBase n)
                    {
                        ServletContext ctx = AlipayNotifyGatewayServlet.this.getServletContext();

                        ctx.log(n.getString());
                        ctx.log(n.getProperties().toString());

                        ctx.log("verify notify... "+n.verify(AlipayFactory.getDefaultInstance().getConf()));

                        return(false);
                    }
                }
            );
        }

        if (Boolean.valueOf(config.getInitParameter("com.github.cuter44.alipay.notifygateway.dryrun")))
        {
            this.gateway.addListener(
                new AlipayNotifyListener(){
                    @Override
                    public boolean handle(NotifyBase n)
                    {
                        return(true);
                    }
                }
            );
        }
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException
    {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        Properties queryProp = new Properties();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            queryProp.setProperty(key, req.getParameter(key));
        }
        if (queryProp.containsKey(KEY_NOTIFY_DATA))
            queryProp.putAll(parseXML(queryProp.getProperty(KEY_NOTIFY_DATA)));

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
