(function () {
    getTownship();

    function getTownship() {
        $.get("/api/townships?cityId=" + $('#city').val(), function (data) {
            if (data) {
                var openSelect = '<select id="townshipId" name="townshipId" class="form-control form-controls" required="required">';
                var option = '';

                for (var i = 0; i < data.length; i++) {
                    option += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
                }
                var closeSelect = '</select>';
                var text = openSelect + option + closeSelect;
                $('#township').html(text);
            }
        });
    }

    $('#city').change(function () {
        getTownship();
    });
})();
