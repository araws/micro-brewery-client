package pl.antygravity.microbreweryclient.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.antygravity.microbreweryclient.web.model.BeerDto;
import pl.antygravity.microbreweryclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "ar.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + uuid.toString());
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public CustomerDto getCustomerById(UUID randomUUID) {
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + randomUUID.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID randomUUID, CustomerDto customerDto) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + randomUUID.toString(), customerDto);
    }

    public void deleteCustomer(UUID randomUUID) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + randomUUID.toString());
    }
}
