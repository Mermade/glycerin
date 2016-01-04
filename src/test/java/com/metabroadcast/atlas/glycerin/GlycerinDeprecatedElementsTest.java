package com.metabroadcast.atlas.glycerin;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.net.HostSpecifier;
import com.metabroadcast.atlas.glycerin.model.Feed;
import com.metabroadcast.atlas.glycerin.model.Feeds;
import com.metabroadcast.atlas.glycerin.queries.FeedsQuery;

public class GlycerinDeprecatedElementsTest {

    private GlycerinHttpClient glycerinClient;
    private HashMultimap<String, String> ignorableDeprecations;

    @Parameters({ "nitro.host", "nitro.apikey" })
    @BeforeClass(groups = "integration")
    public void setup(String host, String apiKey) throws ParseException {
        this.ignorableDeprecations = HashMultimap.create();
        addDeprecatedElementsToTheIgnorableMap();

        glycerinClient = new GlycerinHttpClient(HostSpecifier.from(host), apiKey, null,
                Optional.absent());
    }

    @Test(groups = "integration")
    public void testProgrammeForDeprecatedFields() throws GlycerinException {
        GlycerinQuery<Feeds, Feed> query = new FeedsQuery();
        GlycerinResponse<Feed> response = glycerinClient.get(query);

        response.getResults().stream().forEach(feed -> {
            String feedName = feed.getName();
            feed.getDeprecations().getDeprecated().stream().forEach(deprecatedElement -> {
                if (!ignorableDeprecations.containsEntry(feedName, deprecatedElement.getName())) {
                    fail();
                }
            });
        });
    }

    public void addDeprecatedElementsToTheIgnorableMap() {
        addProgrammesDeprecatedElements();
        addBroadcastsDeprecatedElements();
        addGroupsDeprecatedElements();
        addItemsDeprecatedElements();
        addSchedulesDeprecatedElements();
        addVersionsDeprecatedElements();
        addAvailabilityDeprecatedElements();
        addImagesDeprecatedElements();
        addMasterbrandsDeprecatedElements();
        addPeopleDeprecatedElements();
        addPipsDeprecatedElements();
        addPromotionsDeprecatedElements();
        addServicesDeprecatedElements();
    }

    public void addProgrammesDeprecatedElements() {
        List<String> programmesDeprecatedElements = Lists.newArrayList();
        programmesDeprecatedElements.add("availability_from");
        programmesDeprecatedElements.add("availability_to");
        programmesDeprecatedElements.add("initial_letter_stop");
        programmesDeprecatedElements.add("most_popular");
        programmesDeprecatedElements.add("versions_availability");
        programmesDeprecatedElements.add("people");
        programmesDeprecatedElements.add("titles");
        programmesDeprecatedElements.add("genre_groups");
        programmesDeprecatedElements.add("n:formats");
        programmesDeprecatedElements.add("n:genre_groups");
        programmesDeprecatedElements.add("n:image");

        ignorableDeprecations.putAll("Programmes", programmesDeprecatedElements);
    }

    public void addBroadcastsDeprecatedElements() {
        List<String> broadcastDeprecatedElements = Lists.newArrayList();
        broadcastDeprecatedElements.add("n:image");

        ignorableDeprecations.putAll("Broadcasts", broadcastDeprecatedElements);
    }

    public void addGroupsDeprecatedElements() {
        List<String> groupsDeprecatedElements = Lists.newArrayList();
        groupsDeprecatedElements.add("n:image");

        ignorableDeprecations.putAll("Groups", groupsDeprecatedElements);
    }


    public void addItemsDeprecatedElements() {
        List<String> itemsDeprecatedElements = Lists.newArrayList();
        itemsDeprecatedElements.add("offset");
        itemsDeprecatedElements.add("n:image");

        ignorableDeprecations.putAll("Items", itemsDeprecatedElements);
    }

    public void addSchedulesDeprecatedElements() {
        List<String> schedulesDeprecatedElements = Lists.newArrayList();
        schedulesDeprecatedElements.add("titles");
        schedulesDeprecatedElements.add("n:image");

        ignorableDeprecations.putAll("Schedules", schedulesDeprecatedElements);
    }

    public void addVersionsDeprecatedElements() {
        List<String> versionsDeprecatedElements = Lists.newArrayList();
        versionsDeprecatedElements.add("n:version_types");

        ignorableDeprecations.putAll("Versions", versionsDeprecatedElements);
    }

    /*
    * The following methods adds an empty list to the MultiHashMap because at the moment
    * there are no ignorable deprecated fields for the specific feed types.
    */

    public void addAvailabilityDeprecatedElements() {
        List<String> availabilityDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Availability", availabilityDeprecatedElements);
    }

    public void addImagesDeprecatedElements() {
        List<String> imagesDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Images", imagesDeprecatedElements);
    }

    public void addMasterbrandsDeprecatedElements() {
        List<String> masterbrandDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Masterbrands", masterbrandDeprecatedElements);
    }

    public void addPeopleDeprecatedElements() {
        List<String> peopleDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("People", peopleDeprecatedElements);
    }

    public void addPipsDeprecatedElements() {
        List<String> pipsDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Pips", pipsDeprecatedElements);
    }

    public void addPromotionsDeprecatedElements() {
        List<String> promotionsDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Promotions", promotionsDeprecatedElements);
    }

    public void addServicesDeprecatedElements() {
        List<String> servicesDeprecatedElements = Lists.newArrayList();
        ignorableDeprecations.putAll("Services", servicesDeprecatedElements);
    }
}
