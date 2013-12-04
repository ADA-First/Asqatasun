<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty configProperties['cdnUrl']}">
        <!-- external js -->
        <c:set var="jqueryUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/jquery-1.9.1.min.js"/>
        <c:set var="codePrettifierJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/External-Js/prettify.min.js" scope="request"/>

        <!-- internal js -->
        <c:set var="displayExternalImgJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/result-page/add-img-snapshot-min.js" scope="request"/>
        <c:set var="prettyPrintJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/result-page/pretty-print-min.js" scope="request"/>
        <c:set var="testDetailsJsUrl" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Js/expand-collapse/test-details-min.js" scope="page"/>

        <!-- external images -->
        <c:set var="testInfoLinkImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/test-info-link.png" scope="request"/>
        <c:set var="algoLinkImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/algo-link.png" scope="request"/>
        <c:set var="expandedSmallImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/expanded-s.png" scope="request"/>
        <c:set var="collapsedSmallImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/collapsed-s.png" scope="request"/>
        <c:set var="sourceCodeImg" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/html-source-icon.png" scope="request"/>
        <!-- external images -->
        <c:set var="processingImgUrl" scope="request" value="${pageContext.request.scheme}://${configProperties['cdnUrl']}/Images/processing.gif"/>
    </c:when>
    <c:otherwise>
        <!-- external js -->
        <c:set var="jqueryUrl">
            <c:url value="/External-Js/jquery-1.9.1.min.js"/>
        </c:set>        
        <c:set var="codePrettifierJsUrl" scope="request">
            <c:url value="/External-Js/prettify.min.js"/>
        </c:set> 

        <!-- internal js -->
        <c:set var="displayExternalImgJsUrl" scope="request">
            <c:url value="/Js/result-page/add-img-snapshot-min.js"/>
        </c:set>        
        <c:set var="prettyPrintJsUrl" scope="request">
            <c:url value="/Js/result-page/pretty-print-min.js"/>
        </c:set> 
        <c:set var="testDetailsJsUrl" scope="page">
            <c:url value="/Js/expand-collapse/test-details-min.js"/>
        </c:set>

        <!-- external images -->
        <c:set var="testInfoLinkImg" scope="request">
            <c:url value="/Images/test-info-link.png"/>
        </c:set> 
        <c:set var="algoLinkImg" scope="request">
            <c:url value="/Images/algo-link.png"/>
        </c:set> 
        <c:set var="expandedSmallImg" scope="request">
            <c:url value="/Images/expanded-s.png"/>
        </c:set> 
        <c:set var="collapsedSmallImg" scope="request">
            <c:url value="/Images/collapsed-s.png"/>
        </c:set> 
        <c:set var="sourceCodeImg" scope="request">
            <c:url value="/Images/html-source-icon.png"/>
        </c:set>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="pageTitle" scope="page">
        <fmt:message key="criterionResultPage.pageTitle">
            <fmt:param>
                ${criterionLabel}
            </fmt:param>
            <fmt:param>
                ${url}
            </fmt:param>
        </fmt:message>
    </c:set>
    <c:set var="addJqueryUI" scope="request" value="true"/>
    <%@include file="template/head.jsp" %>
    <body id="tgm-criterion-result-page">
        <%@include file="template/header-utils.jsp" %>
        <div class="container">
            <c:set var="pageName" scope="page">
                <fmt:message key="criterionResultPage.h1">
                    <fmt:param>
                        ${criterionLabel}
                    </fmt:param>
                    <fmt:param>
                        <a href="${url}">${url}</a>
                    </fmt:param>
                </fmt:message>
            </c:set>
            <ul class="breadcrumb">
                <li><a href="<c:url value="/home.html"/>"><fmt:message key="home.h1"/></a> <span class="divider"></span></li>
                <li><a href="<c:url value="/home/contract.html?cr=${cr}"/>">${contractName}</a> <span class="divider"></span></li>
                <c:if test="${authorizedScopeForPageList == 'true'}">
                    <c:set var="auditSynthesisName" scope="page">
                        <fmt:message key="synthesisSite.h1">
                            <fmt:param>
                                ${audit}
                            </fmt:param>
                        </fmt:message>
                    </c:set>
                    <li><a href="<c:url value="/home/contract/audit-synthesis.html?audit=${audit}"/>">${auditSynthesisName}</a> <span class="divider"></span></li>
                    <li><a href="<c:url value="/home/contract/page-list.html?audit=${audit}"/>"><fmt:message key="pageList.h1"/></a> <span class="divider"></span></li>
                    <li><a href="<c:url value="/home/contract/page-list.html?audit=${audit}&amp;status=f2xx"/>"><fmt:message key="pageList.f2xx.h1"/></a> <span class="divider"></span></li>
                </c:if>
                <li><a href="<c:url value="/home/contract/audit-result.html?audit=${audit}"/>"><fmt:message key="resultPage.h1"/></a> <span class="divider"></span></li>
                <li class="active">${pageTitle}</li>
            </ul>
            <div class="row">
                <div class="span16">
                    <h1>
                        ${pageName}
                    </h1>
                </div>
            </div><!-- class="row"-->
            <div class="row">
                <div class="span16 back">
                    <a href="<c:url value="/home/contract/audit-result.html?audit=${audit}"/>" class="back-link">
                        <fmt:message key="criterionResultPage.backToAuditResultByCriterion"/>
                    </a>
                </div>
            </div><!-- class="row"-->
            <c:set var="counterByThemeMap" scope="request" value="${statistics.counterByThemeMap}"/>
            <c:set var="addShowHide" scope="request" value="true"/>
            <c:import url="template/detailed-result.jsp" />
        </div><!-- id="container"-->
        <%@include file="template/footer.jsp" %>
        <script type="text/javascript" src="${jqueryUrl}"></script>
        <script type="text/javascript" src="${testDetailsJsUrl}"></script>
        <script type="text/javascript" src="${displayExternalImgJsUrl}"></script>
        <script type="text/javascript" src="${codePrettifierJsUrl}"></script>
        <script type="text/javascript" src="${prettyPrintJsUrl}"></script>
    </body>
</html>
</compress:html>