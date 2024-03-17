package filters

import play.api.http.HttpFilters
import play.api.mvc.EssentialFilter
import play.filters.csrf.CSRFFilter

import javax.inject.Inject

//csrfFilter: CSRFFilter,

/*

Removed the CS Filter , just to implement it  ::
 */
class Filters @Inject()(
                        authenticationFilter: AuthenticationFilter,
                        logFilter:LoggingFilter) extends HttpFilters {
  def filters: Seq[EssentialFilter] = Seq(authenticationFilter,logFilter)
}