<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Booking Form HTML Template</title>

<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Raleway:400,700" rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Custom stylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>

<body>
<div id="booking" class="section">
    <div class="section-center">
        <div class="container">
            <div class="row">
                <div class="booking-form">
                    <form>
                        <div class="form-group">
                            <label>
                                <input class="form-control" type="text" placeholder="Enter your Name">
                            </label>
                            <span class="form-label">Name</span>
                        </div>
                        <div class="form-group">
                            <label>
                                <input class="form-control" type="tel" placeholder="Enter your Phone number">
                            </label>
                            <span class="form-label">Phone</span>
                        </div>
                        <div class="form-group">
                            <label>
                                <input class="form-control" type="tel" placeholder="Enter an origin location">
                            </label>
                            <span class="form-label">Pickup Location</span>
                        </div>
                        <div class="form-group">
                            <label>
                                <input class="form-control" type="tel" placeholder="Enter a destination location">
                            </label>
                            <span class="form-label">Destination Location</span>
                        </div>
                        <div class="form-group">
                            <label>
                                <select class="form-control" required>
                                    <option value="" selected >Select Passenger Numbers</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                            </label>
                            <span class="select-arrow"></span>
                            <span class="form-label">Passenger Numbers</span>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>
                                        <input class="form-control" type="date" required>
                                    </label>
                                    <span class="form-label">Pickup Date</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>
                                        <input class="form-control" type="time" required>
                                    </label>
                                    <span class="form-label">Pickup Time</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-btn">
                            <button class="submit-btn">Book Now</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script>
    $('.form-control').each(function () {
        floatedLabel($(this));
    });

    $('.form-control').on('input', function () {
        floatedLabel($(this));
    });

    function floatedLabel(input) {
        const $field = input.closest('.form-group');
        if (input.val()) {
            $field.addClass('input-not-empty');
        } else {
            $field.removeClass('input-not-empty');
        }
    }
</script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>