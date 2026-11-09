
<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="card shadow">
                <div class="card-body p-4">

                    <h3 class="text-center mb-3">
                        Create New Password
                    </h3>

                    <p class="text-muted text-center">
                        Enter your new password and confirm it.
                    </p>

                    <form action="${pageContext.request.contextPath}/reset-password"
                          method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                New Password
                            </label>

                            <input type="password"
                                   name="newPassword"
                                   class="form-control"
                                   minlength="8"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Confirm Password
                            </label>

                            <input type="password"
                                   name="confirmPassword"
                                   class="form-control"
                                   minlength="8"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn btn-success w-100">
                            Update Password
                        </button>

                    </form>

                    <c:if test="${not empty message}">
                        <div class="alert alert-info mt-3">
                            ${message}
                        </div>
                    </c:if>

                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
