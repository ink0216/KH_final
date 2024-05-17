document.addEventListener('DOMContentLoaded', function () {
    const personalMemberButton = document.getElementById('personalMember');
    const coparationMemberButton = document.getElementById('coparationMember');

    personalMemberButton.addEventListener('click', function () {
        this.classList.add('selected');
        coparationMemberButton.classList.remove('selected');
    });

    coparationMemberButton.addEventListener('click', function () {
        this.classList.add('selected');
        personalMemberButton.classList.remove('selected');
    });
});