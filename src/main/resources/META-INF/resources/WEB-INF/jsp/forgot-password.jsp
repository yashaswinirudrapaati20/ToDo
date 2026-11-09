
<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow">
                <div class="card-body p-4">

                    <h3 class="text-center mb-3">
                        Forgot Password?
                    </h3>

                    <p class="text-muted text-center">
                        Enter your registered email address.
                        We'll send you a 6-digit OTP.
                    </p>

                    <form action="${pageContext.request.contextPath}/forgot-password"
                          method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                Email Address
                            </label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   placeholder="Enter your Gmail address"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Send OTP
                        </button>

                    </form>

                    <c:if test="${not empty message}">
                        <div class="alert alert-info mt-3">
                            ${message}
                        </div>
                    </c:if>

                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/login">
                            Back to Login
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
