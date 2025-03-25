package com.aemvilla.core.models;

import com.aemvilla.core.bean.NavigationBean;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Model(adaptables = SlingHttpServletRequest.class, adapters = HeaderModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = HeaderModel.RESOURCE_HEADER)

public class HeaderModel {

    static final String RESOURCE_HEADER = "aem-villa/components/header-villa";

    @ChildResource
    Resource navmf;

//    @ValueMapValue
//     List<NavigationBean> getNavigations() {
//        List<NavigationBean> navigation = new ArrayList<>();
//
//        if (navmf != null) {
//            for (Resource nav : navmf.getChildren()) {
//                String navText = nav.getValueMap().get("navText", String.class);
//                String navUrl = nav.getValueMap().get("navUrl", String.class);
//                navigation.add(new NavigationBean(navText, navUrl));
//            }
//        }
//        return navigation;
//    }

    public List<NavigationBean> navigation = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Iterator<Resource> resourceIterator = navmf.listChildren();
        while (resourceIterator.hasNext()) {
            Resource resource = resourceIterator.next();
            String navText = resource.getValueMap().get("navText", String.class);
            String navUrl = resource.getValueMap().get("navUrl", String.class);
            navigation.add(new NavigationBean(navText, navUrl));

        }

    }
}
