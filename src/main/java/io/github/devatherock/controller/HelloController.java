package io.github.devatherock.controller;

import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.devatherock.domain.Accessory;
import io.github.devatherock.service.TimeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
	private final TimeService timeService;
	private final ElasticsearchOperations elasticClient;

	@GetMapping("/hello")
	public String sayHello() {
		Pageable pageable = PageRequest.of(0, 2);
		NativeSearchQuery query = new NativeSearchQueryBuilder()
			    .withSourceFilter(new FetchSourceFilterBuilder().withIncludes().build())
				.withQuery(QueryBuilders.termQuery("total_quantity", "2"))
				.withSorts(new FieldSortBuilder("order_date").order(SortOrder.ASC))
				.withPageable(pageable)
				.build();
		SearchHits<Accessory> results = elasticClient.search(query, Accessory.class);

		return "Hello at " + timeService.getTime() + ". Cluster name: "
				+ elasticClient.cluster().health().getClusterName() + " Content: "
				+ results.stream().map(searchHit -> searchHit.getContent()).collect(Collectors.toList());
	}

}
