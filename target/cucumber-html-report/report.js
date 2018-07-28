$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("ProductComparison.feature");
formatter.feature({
  "line": 1,
  "name": "ProductComparison",
  "description": "",
  "id": "productcomparison",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "ProductComparison1",
  "description": "",
  "id": "productcomparison;productcomparison1",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I login to Opencart Application",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "In Between Steps",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "I Close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "ProductComparison.initialize()"
});
formatter.result({
  "duration": 7891143429,
  "status": "passed"
});
formatter.match({
  "location": "ProductComparison.productcompare()"
});
formatter.result({
  "duration": 57460679606,
  "status": "passed"
});
formatter.match({
  "location": "ProductComparison.closeBrowser()"
});
formatter.result({
  "duration": 340582834,
  "status": "passed"
});
});