package com.github.cuter44.alipay;

import java.util.List;
import java.util.ArrayList;

import com.github.cuter44.alipay.resps.NotifyBase;
import com.github.cuter44.alipay.AlipayNotifyListener;

public class AlipayNotifyPublisher
{
  // FIELDS
    protected List<AlipayNotifyListener> listNl;

  // CONSTRUCT
    public AlipayNotifyPublisher()
    {
        this.listNl = new ArrayList<AlipayNotifyListener>();

        return;
    }

  // EVENTQUEUE
    public void addListener(AlipayNotifyListener l)
    {
        this.listNl.add(l);

        return;
    }

    public void removeListener(AlipayNotifyListener l)
    {
        this.listNl.remove(l);

        return;
    }

    /**
     * @return true if one of the listener reports handled.
     */
    public boolean publish(NotifyBase notice)
    {
        for (AlipayNotifyListener l:this.listNl)
        {
            try
            {
                if (l.handle(notice))
                    return(true);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                continue;
            }
        }

        return(false);
    }
}
