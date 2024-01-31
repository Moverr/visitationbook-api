package filters

import play.api.http.HttpFilters
import play.api.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter

import javax.inject.Inject

class Filters @Inject()(csrfFilter: CSRFFilter,
                        authenticationFilter: AuthenticationFilter,
                        logFilter:LoggingFilter) extends HttpFilters {
  def filters: Seq[EssentialFilter] = Seq(csrfFilter, authenticationFilter,logFilter)
}