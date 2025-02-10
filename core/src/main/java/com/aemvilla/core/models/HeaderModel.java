package com.aemvilla.core.models;

import com.aemvilla.core.bean.NavigationBean;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.ArrayList;
import java.util.List;


@Model(adaptables = Resource.class,
        adapters = HeaderModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = HeaderModel.RESOURCE_HEADER)

public class HeaderModel {

    static final String RESOURCE_HEADER = "aem-villa/components/header-villa";

    @ChildResource
    @ValueMapValue
    private Resource navmf;

    @ValueMapValue
    private List<NavigationBean> getNavigations() {
        List<NavigationBean> navigation = new ArrayList<>();

        if (navmf != null) {
            for (Resource nav : navmf.getChildren()) {
                String navText = nav.getValueMap().get("navText", String.class);
                String navUrl = nav.getValueMap().get("navUrl", String.class);
                navigation.add(new NavigationBean(navText, navUrl));
            }
        }
        return navigation;
    }
}
