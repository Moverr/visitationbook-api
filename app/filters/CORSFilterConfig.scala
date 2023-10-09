package filters

import javax.inject.Inject
import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class CORSFilterConfig @Inject() (corsFilter: CORSFilter) extends DefaultHttpFilters(corsFilter)
