package net.riezebos.graphql.resolvers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import net.riezebos.graphql.generated.DgsConstants;
import net.riezebos.graphql.generated.types.Brand;
import net.riezebos.graphql.repository.MockDataSource;

@DgsComponent
public class BrandDataResolver {

  @Autowired
  private MockDataSource repos;

  @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Brands)
  public List<Brand> brands(@InputArgument(name = "name") Optional<String> name) {

    if (name.isEmpty()) {
      return repos.getBrands();
    }

    return repos.getBrands().stream().filter(brand -> StringUtils.containsIgnoreCase(brand.getName(), name.get())).collect(Collectors.toList());

  }

}
