package com.aemvilla.core.models;


import com.aemvilla.core.bean.NavigationBean;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, adapters = BannerModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = BannerModel.RESOURCE_BANNER)
public class BannerModel {

    public static final String RESOURCE_BANNER = "aem-villa/components/header-villa";

    @ChildResource
    Resource banmf;

    public List<NavigationBean> getBannerDetails() {
        return bannerDetails;
    }

    List<NavigationBean> bannerDetails;

    @PostConstruct

    protected void init() {
        bannerDetails = new ArrayList<>();
        Iterator<Resource> bannerResource = banmf.listChildren();
        while (bannerResource.hasNext()) {
            Resource nextResource = bannerResource.next();
            String title = nextResource.getValueMap().get("banTitle", String.class);
            String description = nextResource.getValueMap().get("banDescription", String.class);
            bannerDetails.add(new NavigationBean(title, description));
        }
    }
}
