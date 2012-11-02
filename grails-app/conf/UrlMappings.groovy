class UrlMappings {

	static mappings = {
        "/rest/media-source/$name"(resource: 'mediaSourceResource')
		"/rest/media-source"(resource: 'mediaSourceResource')
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
